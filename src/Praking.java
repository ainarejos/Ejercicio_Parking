import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Praking {
    ArrayList<String> matriculas = new ArrayList<>();
    BufferedWriter writter;
    BufferedReader reader;
    private int places_no_discapacitats;
    private int places_discapacitat;
    int[] plazas;
    double porcentaje;
    private HashMap<Integer, String> posciciones = new HashMap<>();
    enum TipusPlacesParking{
        Discapacitat,
        No_Discapacitat
    };


    public Praking(int places_no_discapacitats, int places_discapacitat){
        this.places_no_discapacitats=places_no_discapacitats;
        this.places_discapacitat=places_discapacitat;
        plazas=new int[places_discapacitat+places_no_discapacitats];
        porcentaje=(0.85*places_discapacitat);
    };


    //public void llegirMatriculas(int lineas,String path) throws Exception {
        //String matricula;
        //reader = new BufferedReader(new FileReader(path));
        //for (int i = 0; i <lineas ; i++) {
            //matricula=reader.readLine();
            //matriculas.add(matricula);
        //}
    //}

    public void LlegirMatriculas(String path) throws Exception {
        reader = new BufferedReader(new FileReader(path));
        String matricula;
        for (int i = 0; i <plazas.length ; i++) {
            matricula=reader.readLine();
            entraCotxe(matricula);
        }
        reader.close();
    }

    public void GuardarMatriculas(String path) throws IOException {
        writter = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i <matriculas.size() ; i++) {
            writter.write(matriculas.get(i) + "\n");
        }
        writter.close();
    }

    private String generarMatricula(String matricula) throws Exception {
        String generarMatricula=matricula;
        if (matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")&&noRepetido(matricula)){
            matriculas.add(matricula);
        }else if (noRepetido(matricula)==false){
            generarMatricula="noValida";
            System.out.println("El cotxe ja està al parking. No pot entrar.");
            //throw new Exception("El cotxe ja està al parking. No pot entrar.");
        } else {
            throw new Exception("La matricula no es valida");
        }
        return generarMatricula;
    }

    public int entraCotxe(String matricula) throws Exception {
        int plaza=0;
            String a=generarMatricula(matricula);
            int contenedor=0;
            do {
                plaza = (int) (Math.random() * plazas.length);
                contenedor=plazas[plaza];
                if ((disponibilidad(contenedor))&&Lleno()&&(a.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$"))){
                    plazas[plaza] = 1;
                    posciciones.put(plaza, matricula);
                    System.out.println("El coche ha apacado en la placa: " + plaza);
                    if (plaza >= 0 && plaza < getPlaces_discapacitat()) {
                        System.out.println("Garrulo detected!!! Ha aparcat a la plaça: " + plaza);
                    }
                }
            } while ((disponibilidad(contenedor)==false)&&(Lleno())) ;
            if (Lleno()==false){
                throw new Exception("Parking ple");
            }
            if (getPlacesOcupades(TipusPlacesParking.No_Discapacitat)>=(0.85*places_no_discapacitats)){
                System.out.println(" Ocupació de places per no discapacitats supera el 85%");
            }
        return plaza;
    }

    public int entraCotxeDiscapacitat(String matricula) throws Exception {
        int plaza=0;
            String a=generarMatricula(matricula);
            int contenedor=0;
            do {
                plaza = (int) (Math.random() * getPlaces_discapacitat());
                contenedor=plazas[plaza];
                if ((disponibilidad(contenedor))&&discapacitastLleno()&&(a.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$"))){
                    plazas[plaza] = 1;
                    posciciones.put(plaza, matricula);
                    System.out.println("El coche ha apacado en la placa para discapacitados: "+ plaza);
                } else if((discapacitastLleno()==false)){
                    plaza = (int) (Math.random() * plazas.length);
                    contenedor=plazas[plaza];
                    if ((disponibilidad(contenedor))&&Lleno()&&(a.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$"))){
                        plazas[plaza] = 1;
                        posciciones.put(plaza, matricula);
                        throw new Exception("Parking per discapacitats ple. Ha ocupat plaça normal num: " + plaza);
                    }
                }
            } while ((disponibilidad(contenedor)==false)&&(Lleno())) ;
            if (Lleno()==false){
                throw new Exception("Parking per discapacitats ple");
            }
        return plaza;
    }

    public void surtCotxe(String matricula) throws Exception {
        boolean Hola=true;
            for (int i = 0; i < plazas.length ; i++) {
                if (matricula.equals(posciciones.get(i))){
                    int plaza=i;
                    matriculas.remove(matricula);
                    plazas[plaza] = 0;
                    System.out.println("Ha salido el coche con matricula; " + matricula + " de la plaza; " + i);
                    Hola=false;
                }
            }
            if (Hola){
                throw new Exception("El cotxe no és al parking");
            }
    }

    public void surtCotxeDiscapacitats(String matricula) throws Exception {

            boolean Hola=true;
            for (int i = 0; i < getPlaces_discapacitat() ; i++) {
                if (matricula.equals(posciciones.get(i))){
                    int plaza=i;
                    plazas[plaza] = 0;
                    System.out.println("Ha salido el coche con matricula; " + matricula + " de la plaza para discapacitados; " + i);
                    Hola=false;
                } else {
                    throw new Exception("El cotxe no és al parking per discapacitats.");
                }
            }

    }

    public int getPlacesOcupades(TipusPlacesParking tipus){
        int placesOcupades=0;
        if (tipus==TipusPlacesParking.No_Discapacitat){
            for (int i = getPlaces_discapacitat(); i <plazas.length ; i++) {
                if (plazas[i]==1){
                    placesOcupades++;
                }
            }
        } else if (tipus==TipusPlacesParking.Discapacitat){
            for (int i = 0; i <getPlaces_discapacitat() ; i++) {
                if (plazas[i]==1){
                    placesOcupades++;
                }
            }
        }
        return placesOcupades;
    }

    public int getPlacesLliures(TipusPlacesParking tipus){
        int placesLliures=0;
        if (tipus==TipusPlacesParking.No_Discapacitat){
            for (int i = getPlaces_discapacitat(); i <plazas.length ; i++) {
                if (plazas[i]==0){
                    placesLliures++;
                }
            }
        } else if (tipus==TipusPlacesParking.Discapacitat){
            for (int i = 0; i <getPlaces_discapacitat() ; i++) {
                if (plazas[i]==0){
                    placesLliures++;
                }
            }
        }
        return placesLliures;
    }

    private boolean discapacitastLleno(){
        boolean lleno=true;
        int contador=0;
        for (int i = 0; i<=10+1 ; i++) {
            if (plazas[i]==1){
                contador++;
            }
        }
        if (contador==(11)){
            lleno=false;
        }
        return lleno;
    }

    private boolean disponibilidad(int plaza){
        boolean disponibilidad=true;
        if (plaza==1){
            disponibilidad=false;
        }
        return disponibilidad;
    }

    private boolean Lleno(){
        boolean lleno=true;
        int contador=0;
        for (int i = 0; i <plazas.length ; i++) {
            if (plazas[i]==1){
                contador++;
            }
        }
        if (contador==plazas.length){
            lleno=false;
        }
        return lleno;
    }


    private boolean noRepetido(String matricula){
        boolean noRepetido=true;
        for (int i = 0; i < matriculas.size() ; i++) {
            if (matriculas.get(i).equals(matricula)){
                noRepetido= false;
            }
        }
        return noRepetido;
    }

    public int getPlaces_discapacitat() {
        return places_discapacitat;
    }
    public int getPlaces_no_discapacitats() {
        return places_no_discapacitats;
    }
}

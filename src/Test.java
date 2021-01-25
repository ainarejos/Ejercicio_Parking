import java.util.Scanner;

public class Test {
    private static Praking p1=new Praking(10,10);
    public static void main(String[] args) {
        try {
            menu();
            //System.out.println(p1.porcentaje);
            //p1.LlegirMatriculas("Prueba.txt");
            //System.out.println(p1.plazas.length);
            //System.out.println(p1.getPlaces_discapacitat());
            //System.out.println(p1.getPlaces_no_discapacitats());
            //p1.generarMatricula("1234ABC");
            //p1.generarMatricula("3623HFG");
            //p1.generarMatricula("7394ABF");
            //p1.llegirMatriculas(3, "Prueba.txt");
            //p1.entraCotxe("3535HFL");
            //p1.entraCotxe("3835LMN");
            //p1.entraCotxeDiscapacitat("4846LPM");
            //p1.entraCotxe("3835LFN");
            //p1.surtCotxe("3835LFN");
            //p1.surtCotxeDiscapacitats("4846LPM");
            //p1.entraCotxe("3835LQN");
            //p1.entraCotxe("3835ZFN");
            //p1.entraCotxe("3835TFN");
            //p1.entraCotxe("3635LFN");
            //p1.entraCotxe("8435LFN");
            //p1.entraCotxe("3035LFN");
            //p1.entraCotxe("8635LFN");
            //p1.entraCotxe("5486MNO");
            //p1.entraCotxe("5486MkO");
            //p1.entraCotxe("5486MQO");
            //p1.entraCotxe("5486MkS");
            //p1.entraCotxe("5486MkL");
            //p1.entraCotxe("5446MkO");
            //p1.entraCotxe("5486TkO");
            //p1.entraCotxe("5286MkO");
            //p1.entraCotxe("5436MkO");
            //p1.entraCotxe("3486MkO");
            //p1.surtCotxe("5436MkO");
            //p1.entraCotxe("5436MkO");
            //System.out.println(p1.getPlacesOcupades(Praking.TipusPlacesParking.Discapacitat));
            //p1.entraCotxeDiscapacitat("8763YRL");
            //System.out.println(p1.getPlacesOcupades(Praking.TipusPlacesParking.No_Discapacitat));
            //System.out.println(p1.getPlacesOcupades(Praking.TipusPlacesParking.Discapacitat));
            //System.out.println(p1.getPlacesLliures(Praking.TipusPlacesParking.No_Discapacitat));
            //System.out.println(p1.getPlacesLliures(Praking.TipusPlacesParking.Discapacitat));
            //p1.entraCotxeDiscapacitat("5426MkO");
            //p1.entraCotxeDiscapacitat("5986MkO");
            //p1.entraCotxeDiscapacitat("3886MkO");
            //p1.GuardarMatriculas("Hola.txt");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void menu() throws Exception {

            Scanner sc = new Scanner(System.in);
            boolean salir = false;
            String opcion; //opcion que ha elegido el usuario
        try {
            while (!salir) {
                System.out.println("Opción 1: Omplir parking a partir de fitxer");
                System.out.println("Opción 2: Entrar Cotxe");
                System.out.println("Opción 3: Entrar Cotxe Discapacitat");
                System.out.println("Opción 4: Surtir Cotxe");
                System.out.println("Opción 5: Surtir Cotxe Discapacitat");
                System.out.println("Opción 6: Guardar llistat de matricules en fitxer");
                System.out.println("Opción 7: Salir");
                System.out.println("Selecciona una opción: ");
                opcion = sc.nextLine();
                switch (opcion) {
                    case "1":
                        System.out.println("Omplir Parking");
                        System.out.println("Introduzca el path del fichero: ");
                        String path=sc.nextLine();
                        p1.LlegirMatriculas(path);
                        break;
                    case "2":
                        System.out.println("Entrar Cotxe");
                        System.out.println("Introduzca la matricula del coche: ");
                        String matricula=sc.nextLine();
                        p1.entraCotxe(matricula);
                        break;
                    case "3":
                        System.out.println("Entrar Cotxe Discapacitat");
                        entraCotxeDiscapacitas();
                        break;
                    case "4":
                        System.out.println("Surtir Cotxe");
                        surtCotxe();
                        break;
                    case "5":
                        System.out.println("Surtir Cotxe Discapacitat");
                        surtCotxeDiscapacitats();
                        break;
                    case "6":
                        System.out.println("Guardar llistat de matricules en fitxer");
                        guardar();
                        break;
                    case "7":
                        System.out.println("Salir");
                        salir = true;
                        break;
                    default:
                        System.out.println("Selecciona un número entre 1 y 7");
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    private static void entraCotxe(){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Introduzca la matricula del coche: ");
            String matricula=sc.nextLine();
            p1.entraCotxe(matricula);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static  void entraCotxeDiscapacitas(){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Introduzca la matricula del coche: ");
            String matricula=sc.nextLine();
            p1.entraCotxeDiscapacitat(matricula);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void surtCotxe(){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Introduzca la matricula del coche: ");
            String matricula=sc.nextLine();
            p1.surtCotxe(matricula);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void surtCotxeDiscapacitats(){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Introduzca la matricula del coche: ");
            String matricula=sc.nextLine();
            p1.surtCotxeDiscapacitats(matricula);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void guardar(){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Introduzca el path del fichero: ");
            String path=sc.nextLine();
            p1.GuardarMatriculas(path);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}

public class Paciente extends Persona {

    String nauseas;
    String vomito;
    String dolorAbdominal;
    String diarrea;
    String fiebre;

    public Paciente() {
        super();
    }

    public Paciente(String nauseas, String vomito, String dolorAbdominal, String diarrea, String fiebre, String nombre,
            String cedula) {
        super(nombre, cedula);
        this.nauseas = nauseas;
        this.vomito = vomito;
        this.dolorAbdominal = dolorAbdominal;
        this.diarrea = diarrea;
        this.fiebre = fiebre;
    }

    public String diagnosticar() {

        // determinar el diagnostico

        if (nauseas.equals("si") && vomito.equals("si") && dolorAbdominal.equals("si") && fiebre.equals("si")
                && diarrea.equals("si")) {
            return "Staphylococcus aureus";

        } else if (nauseas.equals("si") && vomito.equals("si") && diarrea.equals("si") && fiebre.equals("si")
                && dolorAbdominal.equals("no")) {
            return "Norovirus";

        } else if (nauseas.equals("si") && vomito.equals("si") && diarrea.equals("no") && fiebre.equals("no")
                && dolorAbdominal.equals("no")) {
            return "Bacillus cereus";

        } else if (nauseas.equals("no") && vomito.equals("no") && diarrea.equals("no") && fiebre.equals("si")
                && dolorAbdominal.equals("si")) {
            return "Taenia saginata";

        } else if (nauseas.equals("no") && vomito.equals("si") && diarrea.equals("si") && fiebre.equals("no")
                && dolorAbdominal.equals("no")) {
            return "Rotavirus";

        } else {
            return "Sin diagnostico";

        }
    }
}

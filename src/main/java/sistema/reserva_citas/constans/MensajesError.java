package sistema.reserva_citas.constans;

public class MensajesError {

    //Paciente
    public static final String PACIENTE_NO_ENCONTRADO = "El paciente solicitado no existe en la base de datos";
    public static final String PACIENTE_DNI_DUPLICADO = "El DNI ingresado ya le pertenece a otro paciente";
    public static final String PACIENTE_INACTIVO = "El paciente ya se encuentra eliminado";
    public static final String PACIENTE_ACTIVO = "El paciente ya se encuentra activo";

    //Medico
    public static final String MEDICO_NO_ENCONTRADO = "El médico no existe en la base de datos";
    public static final String MEDICO_DNI_DUPLICADO = "El DNI ingresado ya le pertenece a otro médico";
    public static final String MEDICO_CMP_DUPLICADO = "El número de CMP ingresado ya está registrado en el sistema";
    public static final String MEDICO_INACTIVO = "El médico ya se encuentra inactivo";
    public static final String MEDICO_ACTIVO = "El médico ya se encuentra activo";

}

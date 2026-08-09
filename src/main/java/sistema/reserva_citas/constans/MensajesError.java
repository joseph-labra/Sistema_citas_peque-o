package sistema.reserva_citas.constans;

public class MensajesError {

    //Paciente
    public static final String PACIENTE_NO_ENCONTRADO = "El paciente solicitado no existe en la base de datos";
    public static final String PACIENTE_DNI_DUPLICADO = "El DNI ingresado ya le pertenece a otro paciente";
    public static final String PACIENTE_YA_INACTIVO = "El paciente ya se encuentra eliminado";
    public static final String PACIENTE_YA_ACTIVO = "El paciente ya se encuentra activo";

    //Medico
    public static final String MEDICO_NO_ENCONTRADO = "El médico no existe en la base de datos";
    public static final String MEDICO_DNI_DUPLICADO = "El DNI ingresado ya le pertenece a otro médico";
    public static final String MEDICO_CMP_DUPLICADO = "El número de CMP ingresado ya está registrado en el sistema";
    public static final String MEDICO_YA_INACTIVO = "El médico ya se encuentra inactivo";
    public static final String MEDICO_YA_ACTIVO = "El médico ya se encuentra activo";

    //Recepcionista
    public static final String RECEPCIONISTA_NO_ENCONTRADO = "El recepcionista solicitado no existe en la base de datos";
    public static final String RECEPCIONISTA_DNI_DUPLICADO = "El DNI ingresado ya le pertenece a otro recepcionista";
    public static final String RECEPCIONISTA_YA_INACTIVO = "El recepcionista ya se encuentra eliminado";
    public static final String RECEPCIONISTA_YA_ACTIVO = "El recepcionista ya se encuentra activo";

    //Cita
    public static final String CITA_NO_ENCONTRADA = "La cita no existe en la base de datos";
    public static final String CITA_HORARIO_INVALIDO = "Las citas solo pueden programarse en punto: (:00) o y medio (:30)";
    public static final String CITA_CRUCE_HORARIOS = "El médico ya tiene una cita programada en ese horario";
    public static final String CITA_NO_EDITABLE = "La cita ya fue completada o cancelada y no se puede modificar";
    public static final String PACIENTE_INACTIVO = "No se puede agendar la cita porque el paciente esta inactivo";
    public static final String MEDICO_INACTIVO = "No se puede agendar la cita porque el medico esta inactivo";
    public static final String RECEPCIONISTA_INACTIVO = "No se puede agendar la cita porque el recepcionista esta inactivo";
}

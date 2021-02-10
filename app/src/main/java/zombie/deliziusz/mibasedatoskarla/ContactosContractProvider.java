package zombie.deliziusz.mibasedatoskarla;

public class ContactosContractProvider {

    public static final String CONTENT_URI_CONTACTOS =
            "content://zombie.deliziusz.mibasedatoskarla/contactos";


    public static final  String [] PROJECTION_CONTACTOS
            = {
            "_id", "usuario", "email", "tel"
    };

    public static final  String FIELD_ID = "_id";
    public static final  String FIELD_USUARIO = "usuario";
    public static final  String FIELD_EMAIL = "email";

}

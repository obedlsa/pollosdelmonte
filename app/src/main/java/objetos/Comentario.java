package objetos;


 /* Clase Comentario.
         3  *
         4  * @author Obed Alvarado
        5  * @version 18/05/2018
*/
public class Comentario {
    private String comentario,email,fecha,nombre,telefono;

     /**
                 * Constructor.
                 * @param comentario: comentario.
                  * @param email: email.
                  * @param fecha: fecha.
                   * @param nombre: nombre.
                    * @param telefono: fecha.
                  */
    public Comentario(String comentario, String email, String fecha, String nombre, String telefono) {
        this.comentario = comentario;
        this.email = email;
        this.fecha = fecha;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public Comentario(){}
     /**
            * Getter.
            * @return comentario: comentario.
            */
    public String getComentario() {
        return comentario;
    }
     /**
      * Setter.
      * @param comentario: comentario.
      */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
     /**
      * Getter.
      * @return email: email.
      */

    public String getEmail() {
        return email;
    }
     /**
      * Setter.
      * @param email: email.
      */
    public void setEmail(String email) {
        this.email = email;
    }
     /**
      * Getter.
      * @return fecha: fecha.
      */

    public String getFecha() {
        return fecha;
    }
     /**
      * Setter.
      * @param fecha: comentario.
      */

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
     /**
      * Getter.
      * @return nombre: nombre.
      */

    public String getNombre() {
        return nombre;
    }
     /**
      * Setter.
      * @param nombre: nombre.
      */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     /**
      * Getter.
      * @return telefono: telefono.
      */

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

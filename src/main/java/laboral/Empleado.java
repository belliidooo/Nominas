package laboral;

public class Empleado extends Persona{

    /**
     * Propiedades de empleado (usando
     * tambien las de personas con el extends)
     * */
    private int categoria;
    public int anyos;

    /**
     * Constructor con los parametros de persona
     * mas los creados anteriormente. Tambien usamos
     * if para que los datos introducidos sean correctos
     * @param nombre Nombre del empleado
     * @param dni DNI del empleado
     * */
    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos)  throws DatosNoCorrectosException{
        super(nombre, dni, sexo);
        if (categoria < 1 || categoria > 10 || anyos < 0) {
            throw new DatosNoCorrectosException();
        }
        this.categoria = categoria;
        this.anyos = anyos;
    }

    /**
     * Constructor de empleados pero definiendo la
     * categoria y los años si no son introducidos
     * @param nombre Nombre del empleado
     * @param dni DNI del empleado
     * @param sexo Sexo del empleado
     * */
    public Empleado(String nombre, char sexo, String dni) throws DatosNoCorrectosException {
        this(nombre, dni, sexo, 1, 0);
    }

    public Empleado() {
        super();
    }

    /**
     * Este metodo fija la categoria siguiendo las
     * restricciones del if. Lanzando una excepcion en
     * caso erroneo
     * @param nuevaCategoria Nueva categoria del empleado
     * @throws DatosNoCorrectosException Si la categoria no esta entre 1 y 10
     * */
    public void setCategoria(int nuevaCategoria) throws DatosNoCorrectosException{
        if (nuevaCategoria < 1 || nuevaCategoria > 10) {
            throw new DatosNoCorrectosException();
        }
        categoria = nuevaCategoria;
    }

    /**
     * Obtiene la categoria del empleado.
     * @return Categoría del empleado.
     */
    public int getCategoria() {
        return categoria;
    }

    /** Incrementa en un año la antigüedad del empleado */
    public void incrAnyo() {
        anyos++;
    }

    /** Muestra por pantalla todos los datos del empleado */
    public void imprime() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Sexo: " + sexo);
        System.out.println("Categoría: " + categoria);
        System.out.println("Años trabajados: " + anyos);
    }

    /** @return años trabajados */
    public int getAnyos() { return anyos; }
}

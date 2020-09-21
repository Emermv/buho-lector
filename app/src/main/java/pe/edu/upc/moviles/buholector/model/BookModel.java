package pe.edu.upc.moviles.buholector.model;

public class BookModel {
    private int id,paginas,propietario;
    private String titulo,autor,lenguaje,imagen,resumen,estado;

    public BookModel() {
    }

    public BookModel(int id, int paginas, int propietario, String titulo, String autor, String lenguaje, String imagen, String resumen, String estado) {
        this.id = id;
        this.paginas = paginas;
        this.propietario = propietario;
        this.titulo = titulo;
        this.autor = autor;
        this.lenguaje = lenguaje;
        this.imagen = imagen;
        this.resumen = resumen;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", paginas=" + paginas +
                ", propietario=" + propietario +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", lenguaje='" + lenguaje + '\'' +
                ", imagen='" + imagen + '\'' +
                ", resumen='" + resumen + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

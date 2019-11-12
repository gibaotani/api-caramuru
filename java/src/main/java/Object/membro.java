package Object;
import java.util.Date;

public class membro {
    private  String Nome;
    private  String CPF;
    private  String Registro_UEB;
    private  Date dt_nascimento;
    private  String link_foto;

    public membro(String Nome, String CPF, String Registro_UEB, Date dt_nascimento ){
        this.setNome(Nome);
        this.setCPF(CPF);
        this.setRegistro_UEB(Registro_UEB);
        this.setDate(dt_nascimento);
        this.setLink_foto(link_foto);
    }
    public  membro(){}

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRegistro_UEB() {
        return Registro_UEB;
    }

    public void setRegistro_UEB(String registro_UEB) {
        this.Registro_UEB = registro_UEB;
    }

    public Date getDate() {
        return dt_nascimento;
    }

    public void setDate(Date date) {
        this.dt_nascimento = date;
    }

    public String getLink_foto(){return link_foto;}

    public void setLink_foto(String link_foto){this.link_foto = link_foto;}
}

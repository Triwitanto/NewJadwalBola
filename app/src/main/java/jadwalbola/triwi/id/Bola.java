package jadwalbola.triwi.id;

/**
 * Created by TRW on 3/13/2018.
 */

public class Bola {
    private String tanggal;
    private String jadwal;
    private  String tv;

    public Bola (String tanggal, String jadwal, String tv)
    {
        this.setTanggal(tanggal);
        this.setJadwal(jadwal);
        this.setTv(tv);
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }
}

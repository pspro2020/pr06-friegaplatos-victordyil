import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import static java.util.Calendar.*;

public class Platos {

    private ArrayList<Integer> platosSucios = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    private ArrayList<Integer> platosMojados = new ArrayList<Integer>();
    private ArrayList<Integer> platosSecos = new ArrayList<Integer>();
    private ArrayList<Integer> alacena = new ArrayList<Integer>();
    Calendar now = getInstance();

    public synchronized int cogerSucio() throws InterruptedException {
        while (platosSucios.isEmpty()) {
            wait();
        }
        now = getInstance();
        System.out.printf("%d:%d:%d - %s: cogido el plato sucio #%d\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), platosSucios.get(0));
        return platosSucios.remove(0);
    }

    public synchronized void ponerMojado(int plato) {
        platosMojados.add(plato);
        now = getInstance();
        System.out.printf("%d:%d:%d - %s: puesto el plato mojado #%d \n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), plato);

        notifyAll();
    }

    public synchronized int cogerSeco() throws InterruptedException {
        while (platosSecos.isEmpty()) {
            wait();
        }
        now = getInstance();
        System.out.printf("%d:%d:%d - %s: cogido el plato seco #%d\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), platosSecos.get(0));

        return platosSecos.remove(0);
    }

    public synchronized void organizar(int plato) {
        alacena.add(plato);
        now = getInstance();
        System.out.printf("%d:%d:%d - %s: puesto un plato #%d en la alacena\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), plato);
        notifyAll();
    }

    public synchronized int cogerMojado() throws InterruptedException {
        while (platosMojados.isEmpty()) {
            wait();
        }
        now = getInstance();
        System.out.printf("%d:%d:%d - %s: cogido el plato mojado #%d\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), platosMojados.get(0));
        return platosMojados.remove(0);
    }

    public synchronized void ponerSeco(int platoId) {
        platosSecos.add(platoId);
        now = getInstance();
        System.out.printf("%d:%d:%d - %s: puesto el plato seco #%d \n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), platoId);
        notifyAll();
    }
}

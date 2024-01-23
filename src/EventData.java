/**
 * @param event 1 Philosopher dies
 *              2 Philosopher grabs right fork
 *              3 Philosopher grabs left fork
 *              4 Philosopher drops right fork
 *              5 Philosopher drops left fork
 */
public record EventData(int event, int philosopherID) {
}

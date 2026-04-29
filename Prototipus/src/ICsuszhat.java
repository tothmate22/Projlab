/**
 * Az ICsuszhat interfész azokat a járműveket jelöli,
 * amelyek jeges útszakaszon megcsúszhatnak és ütközhetnek.
 */
public interface ICsuszhat {

    /**
     * A jármű megcsúszásának kezelése.
     */
    void kicsuszik();

    /**
     * A jármű ütközésének kezelése.
     *
     * @param masik az a jármű, amellyel az ütközés történt
     */
    void utkozott(Jarmu masik);
}

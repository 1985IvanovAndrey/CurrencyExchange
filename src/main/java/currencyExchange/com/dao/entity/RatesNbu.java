package currencyExchange.com.dao.entity;

import lombok.Data;

@Data
public class RatesNbu implements Comparable<RatesNbu>{
    private int id;
    private String nameCurrency;
    private Double rate;



    public RatesNbu(int id, String nameCurrency, Double rate) {
        this.id = id;
        this.nameCurrency = nameCurrency;
        this.rate = rate;
    }

    public RatesNbu() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RatesNbu ratesNbu = (RatesNbu) o;

        if (id != ratesNbu.id) return false;
        if (nameCurrency != null ? !nameCurrency.equals(ratesNbu.nameCurrency) : ratesNbu.nameCurrency != null)
            return false;
        return rate != null ? rate.equals(ratesNbu.rate) : ratesNbu.rate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (nameCurrency != null ? nameCurrency.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatesNbu{" +
                "id=" + id +
                ", nameCurrency='" + nameCurrency + '\'' +
                ", rate=" + rate +
                '}';
    }

    @Override
    public int compareTo(RatesNbu ratesNbu) {
        return nameCurrency.compareTo(ratesNbu.nameCurrency);
    }
}

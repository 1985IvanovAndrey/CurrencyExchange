package currencyExchange.com.dao.entity;

import lombok.Data;

@Data
public class UserOperation {

    private int id;
    private String nameUser;
    private String currency;
    private String operation;
    private double sumOperation;
    private double rate;
    private double outputAmount;
    private String status;
    private String date;

    public UserOperation(String nameUser, String currency, String operation, double sumOperation, double rate, double outputAmount, String status, String date) {
        this.nameUser = nameUser;
        this.currency = currency;
        this.operation = operation;
        this.sumOperation = sumOperation;
        this.outputAmount = outputAmount;
        this.status = status;
        this.rate = rate;
        this.date = date;
    }

    public UserOperation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserOperation that = (UserOperation) o;

        if (id != that.id) return false;
        if (Double.compare(that.sumOperation, sumOperation) != 0) return false;
        if (Double.compare(that.rate, rate) != 0) return false;
        if (Double.compare(that.outputAmount, outputAmount) != 0) return false;
        if (nameUser != null ? !nameUser.equals(that.nameUser) : that.nameUser != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + id;
        result = 31 * result + (nameUser != null ? nameUser.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        temp = Double.doubleToLongBits(sumOperation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(outputAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserOperation{" +
                "id=" + id +
                ", nameUser='" + nameUser + '\'' +
                ", currency='" + currency + '\'' +
                ", operation='" + operation + '\'' +
                ", sumOperation=" + sumOperation +
                ", rate=" + rate +
                ", outputAmount=" + outputAmount +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

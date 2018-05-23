package ar.edu.uns.cs.trabajosocialform.Transactions;

public enum TransactionOptions {

    INSERT(1),
    DELETE(2);

    private final int value;

    private TransactionOptions(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

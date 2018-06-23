package ar.edu.uns.cs.trabajosocialform.Transactions;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Represent a transaction to do in the external database. When a form is inserted, updated or deleted
 * locally, the external database must update as well so the transactions are save to make it later
 */
@Entity(tableName = "transactions", primaryKeys={"transaction_id","formulario_id"})
public class Transaction implements Serializable {


    @ColumnInfo(name = "transaction_id")
    private int transactionId;
    @ColumnInfo(name = "formulario_id")
    private int formId;

    public Transaction(int transactionId, int formId){
        this.transactionId = transactionId;
        this.formId = formId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }
}

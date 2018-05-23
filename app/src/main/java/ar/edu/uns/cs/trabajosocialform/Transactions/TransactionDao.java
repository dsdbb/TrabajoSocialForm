package ar.edu.uns.cs.trabajosocialform.Transactions;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Apoderado;

@Dao
public interface TransactionDao {

    @Insert
    long insert(Transaction transaction);

    @Update
    void update(Transaction... transaction);

    @Delete
    void delete(Transaction... transaction);

    @Query("SELECT * FROM transactions WHERE formulario_id =:formId AND transaction_id=:opc")
    Transaction findTransaction(int opc, int formId);

    @Query("SELECT * FROM transactions")
    List<Transaction> getTransactions();

}

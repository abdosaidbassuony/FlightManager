package com.example.flightmanager.data.database

import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flightmanager.data.model.BoardingPassModel

@Dao
interface BoardingPassDao {
    @Insert
    fun insertBoardPass( insertBoardPass: BoardingPassModel)

    @Delete
    fun deleteBoardPass(deleteBoardPass: BoardingPassModel)

    @Update
    fun updateBoardPass( updateBoardingPass: BoardingPassModel)

    @Query("select * from board_pass")
    fun getAllBoardPass():LiveData<List<BoardingPassModel>>

    @Query("select * from board_pass where id LIKE :id")
    fun getBoardPass(id: Int):BoardingPassModel
    @Query("select id from board_pass")
    fun getAllId():List<Int>

    @Query("DELETE FROM board_pass")
    fun deleteAll()


}
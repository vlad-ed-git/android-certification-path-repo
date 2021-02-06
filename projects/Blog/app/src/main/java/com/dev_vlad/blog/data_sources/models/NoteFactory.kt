package com.dev_vlad.blog.data_sources.models

import com.dev_vlad.blog.utils.DateUtil
import java.util.*
import kotlin.collections.ArrayList

object NoteFactory {

    fun createSingleNote(
            note_id : String? = null,
            note_title:String,
            note_body: String? = null
    ): Note{
        return Note(
                id = note_id ?: UUID.randomUUID().toString(),
                title = note_title,
                body = note_body ?: "",
                created_at = DateUtil.getCurrentTimestampAsStr(),
                updated_at = DateUtil.getCurrentTimestampAsStr()
        )
    }

    //only for testing
    fun createNoteList(numNotes: Int) : List<Note>{
        val list = ArrayList<Note>()
        for (i in 0 until numNotes){
            list.add(
                 createSingleNote(
                      note_id = null,
                      note_title = "Note $i",
                      note_body = "This is a note $i"
                 )
            )
        }
        return list
    }
}
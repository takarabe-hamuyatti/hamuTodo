package com.example.common.task.unDoneTask

import com.example.common.task.archive.DoneTask
import com.example.common.task.unDoneTask.UnDoneTask.Companion.POSTPONE_ABLE_COUNT

data class PostponeAbleUnDoneTask(
    override val id: Long = 0,
    override val name: String,
    override val unixDueDate: Long,
    val postPoneCount: Int = 0
) : UnDoneTask {
    override fun doneTask(unixDoneDate : Long): DoneTask {
        return DoneTask(
            id = id,
            name = name,
            unixDueDate = unixDueDate,
            unixDoneDate = unixDoneDate
        )
    }

    fun postPone(): UnDoneTask {
        return if (postPoneCount < POSTPONE_ABLE_COUNT) {
            this.copy(
                postPoneCount = postPoneCount.plus(1)
            )
        } else {
            WithDeadLineTask(
                id = id,
                name = name,
                unixDueDate = unixDueDate
            )
        }
    }
}
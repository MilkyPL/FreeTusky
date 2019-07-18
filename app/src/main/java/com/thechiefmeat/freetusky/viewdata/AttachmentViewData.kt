package com.thechiefmeat.freetusky.viewdata

import android.os.Parcelable
import com.thechiefmeat.freetusky.entity.Attachment
import com.thechiefmeat.freetusky.entity.Status
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AttachmentViewData(
        val attachment: Attachment,
        val statusId: String,
        val statusUrl: String
) : Parcelable {
    companion object {
        @JvmStatic
        fun list(status: Status): List<AttachmentViewData> {
            val actionable = status.actionableStatus
            return actionable.attachments.map {
                AttachmentViewData(it, actionable.id, actionable.url!!)
            }
        }
    }
}
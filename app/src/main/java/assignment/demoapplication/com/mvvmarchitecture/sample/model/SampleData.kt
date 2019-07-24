package assignment.demoapplication.com.mvvmarchitecture.sample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SampleData {
    @SerializedName("used")
    @Expose
    private var used: Boolean? = null
    @SerializedName("source")
    @Expose
    private var source: String? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("deleted")
    @Expose
    private var deleted: Boolean? = null
    @SerializedName("_id")
    @Expose
    private var id: String? = null
    @SerializedName("__v")
    @Expose
    private var v: Int? = null
    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null
    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null
    @SerializedName("user")
    @Expose
    private var user: String? = null
    @SerializedName("sendDate")
    @Expose
    private var sendDate: String? = null

    fun getUsed(): Boolean? {
        return used
    }

    fun setUsed(used: Boolean?) {
        this.used = used
    }

    fun getSource(): String? {
        return source
    }

    fun setSource(source: String) {
        this.source = source
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getDeleted(): Boolean? {
        return deleted
    }

    fun setDeleted(deleted: Boolean?) {
        this.deleted = deleted
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getV(): Int? {
        return v
    }

    fun setV(v: Int?) {
        this.v = v
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String) {
        this.updatedAt = updatedAt
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
    }

    fun getUser(): String? {
        return user
    }

    fun setUser(user: String) {
        this.user = user
    }

    fun getSendDate(): String? {
        return sendDate
    }

    fun setSendDate(sendDate: String) {
        this.sendDate = sendDate
    }
}
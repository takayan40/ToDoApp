package io.github.takayan40.todoapp

import android.os.Parcel
import android.os.Parcelable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

@Entity
class Todo(
    var title: String = "",
    var detail: String = "",
    var priority: Long = 0,
    var deadline: Date? = null,
    var registed: Date? = null,
    var group: String = ""
) : Parcelable {
    @Id
    var id: Long = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString().toString()
        detail = parcel.readString().toString()
        priority = parcel.readLong()
        deadline = parcel.readSerializable() as Date
        registed = parcel.readSerializable() as Date
        group = parcel.readString().toString()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeLong(id)
        out.writeString(title)
        out.writeString(detail)
        out.writeLong(priority)
        out.writeSerializable(deadline)
        out.writeSerializable(registed)
        out.writeString(group)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Todo> {
        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(parcel)
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }
}
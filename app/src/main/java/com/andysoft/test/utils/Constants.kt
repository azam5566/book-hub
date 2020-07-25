package com.andysoft.test.utils

class Constants {

    interface Permissions {
        companion object {
            const val READ_CONTACT_REQ = 21
            const val RESULT_PICK_CONTACT = 22
        }
    }

    interface SharedPrefTags {
        companion object {
            const val myPrefKey: String = "PreferenceCOVID"
            const val SELECTED_LOCALE = "SELECTED_LOCALE"

        }
    }

    interface TrueFalseStrings {
        companion object {
            const val TRUE = "true"
            const val FALSE = "false"
        }
    }




    interface Data{
        companion object{
            const val IS_AUTHOR_ADDED = "IS_AUTHOR_ADDED"
        }
    }
}
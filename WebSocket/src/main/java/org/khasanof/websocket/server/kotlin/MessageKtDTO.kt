package org.khasanof.websocket.server.kotlin

data class MessageKtDTO(var from: String, var text: String) {

    @Override
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageKtDTO

        if (from != other.from) return false
        return text == other.text
    }

    @Override
    override fun hashCode(): Int {
        var result = from.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }

    @Override
    override fun toString(): String {
        return "MessageKtDTO(from='$from', text='$text')"
    }

}

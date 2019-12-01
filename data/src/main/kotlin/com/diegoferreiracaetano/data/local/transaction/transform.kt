package com.diegoferreiracaetano.data.local.transaction

import com.diegoferreiracaetano.data.local.card.transform
import com.diegoferreiracaetano.data.local.user.transform
import com.diegoferreiracaetano.domain.transaction.StatusTransaction
import com.diegoferreiracaetano.domain.transaction.Transaction
import java.util.Date

internal fun TransactionEntity.transform() = Transaction(id, Date(date), value, success, StatusTransaction.valueOf(status), user.transform(), card.transform())

internal fun Transaction.transform() = TransactionEntity(id, date.time, value, success, status.name, user.transform(), card!!.transform())

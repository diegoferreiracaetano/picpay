package com.diegoferreiracaetano.data.local.card

import com.diegoferreiracaetano.domain.card.Card

internal fun CardEntity.transform() = Card(id, brand, number, name, date, cvv)

internal fun Card.transform() = CardEntity(0, number, brand, name, date, cvv)

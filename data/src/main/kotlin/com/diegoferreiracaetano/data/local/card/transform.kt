package com.diegoferreiracaetano.data.local.card

import com.diegoferreiracaetano.domain.card.Card

internal fun CardEntity.transform() = Card(brand, number, name, date, cvv)

internal fun Card.transform() = CardEntity(brand, number, name, date, cvv)
package no.mhl.wikitopics.repository

import no.mhl.wikitopics.api.WikiService
import javax.inject.Inject

class HomeRepository
@Inject
constructor(
    private val wikiService: WikiService
)
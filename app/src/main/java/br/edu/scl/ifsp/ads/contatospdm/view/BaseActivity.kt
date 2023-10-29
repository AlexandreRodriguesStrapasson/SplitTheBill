package br.edu.scl.ifsp.ads.contatospdm.view

import androidx.appcompat.app.AppCompatActivity

sealed class BaseActivity: AppCompatActivity() {
    protected val EXTRA_PERSON = "Person"
    protected val EXTRA_VIEW_PERSON = "ViewPerson"
    protected val EXTRA_SPLIT = "Split"
}
package com.lucasginard.pelispedia.login.signUp.presenter

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SignUpPresenterTest {

    lateinit var presenter:SignUpPresenter

    @Before
    fun setUp() {
        presenter = SignUpPresenter()
    }

    @Test
    fun `validate login no contains spaces`() {
        assertFalse(presenter.validateInputs("",""))
        assertFalse(presenter.validateInputs("test ","test2"))
        assertFalse(presenter.validateInputs("test","test2 "))
    }

    @Test
    fun `validate login no contains spaces success`() {
        assertTrue(presenter.validateInputs("usuario","admin"))
        assertTrue(presenter.validateInputs("test","test2"))
    }

}
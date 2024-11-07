package com.valoy.disney

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineMainDispatcherRule(
    val dispatcher: TestDispatcher
) : TestRule {

    override fun apply(
        base: org.junit.runners.model.Statement?,
        description: Description?
    ): org.junit.runners.model.Statement {
        return object : org.junit.runners.model.Statement() {
            override fun evaluate() {
                Dispatchers.setMain(dispatcher)

                base?.evaluate()

                Dispatchers.resetMain()
            }
        }
    }
}
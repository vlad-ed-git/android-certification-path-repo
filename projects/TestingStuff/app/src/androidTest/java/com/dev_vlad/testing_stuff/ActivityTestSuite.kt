package com.dev_vlad.testing_stuff

import org.junit.runner.RunWith
import org.junit.runners.Suite


//run tests in group
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    DetailsActivityTest::class
)
class ActivityTestSuite
[![Main Commit](https://github.com/android-alatan/Alerts/actions/workflows/lib-main-branch.yml/badge.svg?branch=main)](https://github.com/android-alatan/Alerts/actions/workflows/lib-main-branch.yml)
[![Release](https://jitpack.io/v/android-alatan/alerts.svg)](https://jitpack.io/#android-alatan/alerts)
# Alerts

Toast and Dialog is popular tools to show notification on screen.

It requires to use Context. It makes test hard if devs use it purely.

This repo is to provide wrapped toast and dialog. The wrapped components are 

## Toast

### Installation
```kotlin
implementation("com.github.android-alatan.alerts:app-toast-api:$version")
implementation("com.github.android-alatan.alerts:app-toast:$version")
testImplementation("com.github.android-alatan.alerts:app-toast-assertion:$version")

implementation("com.github.android-alatan.alerts:common-dialog-api:$version")
implementation("com.github.android-alatan.alerts:common-dialog:$version")

implementation("com.github.android-alatan.alerts:compose-dialog-api:$version")
implementation("com.github.android-alatan.alerts:compose-dialog:$version")

testImplementation("com.github.android-alatan.alerts:common-dialog-assertion:$version")
```

### Example
```kotlin
// in production
class FooViewModel(private val toastBuilderFactory: AppToastBuilderFactory) {
  fun showToast() {
    toastBuilderFactory.create()
        .text("Hello World")
        .duration(AppToastBuilder.Duration.Short)
        .create()
        .show()
  }
}

// in test
class FooTest { 
  private val toastBuilderFactory = MockAppToastBuilderFactory()
  @Test
  fun showToast() {
    viewModel.showToast()
    assertEqual(1, toastBuilderFactory.createCount)
    assertEqual("Hello World", toastBuilderFactory.createdBuilder.text)
    assertEqual(AppToastBuilder.Duration.Short, toastBuilderFactory.createdBuilder.duration)
    assertTrue(toastBuilderFactory.createdBuilder.created)
    assertEqual(1, toastBuilderFactory.createdBuilder.createdToast.showCount)
  }
}
```
The benefit of usage of `app-toast` module is testable.

## AlertDialog

### Installation
```kotlin
implementation("com.github.android-alatan.alerts:common-dialog-api:$version")
implementation("com.github.android-alatan.alerts:common-dialog:$version")

testImplementation("com.github.android-alatan.alerts:common-dialog-assertion:$version")
```

### Example
```kotlin
class FooViewModel(private val alertDialogBuilderFactory: AlertDialogBuilderFactory) {
  fun showDialog() {
      alertDialogBuilderFactory.create()
        .title("Hello World")
        .message("Message!!")
        .setPositiveButton("OK")
        .build()
        .show()
  }
}

// in test
class FooTest {
    private val alertDialogBuilderFactory = MockAlertDialogBuilderFactory()
    @Test
    fun showDialog() {
        viewModel.showDialog()
        assertEqual(1, alertDialogBuilderFactory.createCount)
        assertEqual("Hello World", alertDialogBuilderFactory.lastDialogBuilder.title)
        assertEqual("Message!!", alertDialogBuilderFactory.lastDialogBuilder.message)
        assertEqual("OK", alertDialogBuilderFactory.lastDialogBuilder.positiveButtonText)
        assertNull(alertDialogBuilderFactory.lastDialogBuilder.positiveButtonClickListener)
        assertEqual(1, alertDialogBuilderFactory.lastDialogBuilder.buildCount)
        assertEqual(1, alertDialogBuilderFactory.lastDialogBuilder.lastBuiltDialog.showCount)
    }
}
```

## AlertDialog for Compose

### Installation
```kotlin
implementation("com.github.android-alatan.alerts:compose-dialog-api:$version")
implementation("com.github.android-alatan.alerts:compose-dialog:$version")

testImplementation("com.github.android-alatan.alerts:common-dialog-assertion:$version")
```

### Example
Basically, prerequisite is same as `common-dialog`.  But there is one more required step
```kotlin
@Composable
fun RootComposableFunction() {
    composeAlertDialogBuilderFactory.activate()
}
```
This will observe showState of ComposeAlertDialogBuilderFactory by itself.
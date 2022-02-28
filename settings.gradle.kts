dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
            content {
                includeGroupByRegex("com\\.github\\.android-alatan.*")
            }
        }

    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Alerts"

include(
    ":app-toast",
    ":app-toast-api",
    ":app-toast-assertion",
)

include(
    ":common-dialog",
    ":common-dialog-api",
    ":common-dialog-assertion",
)

include(
    ":compose-dialog",
    ":compose-dialog-api",
)


val modules = hashMapOf<String, String>()

rootProject.projectDir.listFiles()
        ?.forEach {
            findSubProjects(it)
        }

fun findSubProjects(
        file: File
) {
    if (file.name.startsWith(".")) {
        return
    }

    if (file.name == "build.gradle.kts" || file.name == "build.gradle") {
        modules[file.parentFile.name] = file.parentFile.path
        return
    }

    if (file.isDirectory) {
        file.listFiles()
                ?.forEach {
                    findSubProjects(it)
                }
    }
}

for (project in rootProject.children) {

    if (modules.containsKey(project.name)) {
        val directory = modules[project.name] ?: continue
        project.projectDir = File(directory)
    }
}

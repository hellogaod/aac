
#  问题解答

1. 查看依赖树：
//查看单独模块的依赖
gradlew app:dependencies

2. 防止版本冲突，强制使用某一个版本，在项目根目录下使用


    allprojects {

        ...

        //强制使用指定版本
        configurations.all {

            resolutionStrategy {

                force 'org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.21'
                force 'org.jetbrains:annotations:20.1.0'
            }

        }
    }

    解决class类冲突，在冲突的模块中添加，参考app的build.gradle
    packagingOptions {
            // 处理三方class类冲突
            pickFirst 'kotlin/coroutines/coroutines.kotlin_builtins'
    }

3. 使用代码生成工具，别忘了添加如下依赖：

        implementation "com.google.auto.service:auto-service:1.0-rc3"
        annotationProcessor "com.google.auto.service:auto-service:1.0-rc3"

4.Caused by: java.lang.ClassNotFoundException: com.google.common.collect.Multimap

    查看具体问题：Caused by: java.lang.NoClassDefFoundError: com/google/common/collect/Multimap
           	at org.gradle.api.internal.tasks.compile.AnnotationProcessingCompileTask.instantiateProcessor

    见名知意：在org.gradle...的类中找不到Multimap类。那么就是两个版本对应不上。

    去gradle插件中找当前com.google.common.collect.Multimap类

    注意：gradle插件中的依赖版本，①当前build.gradle中会提示（如果没有使用deps.xx格式的话）；②在.gradle/wrapper/dist/gradle版本中看；

    查看具体报错问题如下：
    !()[https://img-blog.csdn.net/20180604131814582?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NzZG5fbW0=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70]

# demo涉及到知识点

1. room及系列

 - (1)完成sqlit①sqlit；②sqlit-framework,其他也不要放过 - 字面意思的理解已完成

 - (2)room：①common；②compiler；③compiler-processing；④migration；⑤runtime；⑥rxjava2

2. lifecycle及系列

3. rxjava2

4.appcompat及系列
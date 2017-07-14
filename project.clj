(defproject glfw "0.1.0-SNAPSHOT"
  :description "Clojure bindings to the GLFW windowing system"
  :url "https://github.come/bcbradle/glfw"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.lwjgl/lwjgl "3.1.2" :classifier "natives-linux" :native-prefix ""]
                 [org.lwjgl/lwjgl "3.1.2" :classifier "natives-macos" :native-prefix ""]
                 [org.lwjgl/lwjgl "3.1.2" :classifier "natives-windows" :native-prefix ""]
                 [org.lwjgl/lwjgl-vulkan "3.1.2"]
                 [org.lwjgl/lwjgl-glfw "3.1.2"]])

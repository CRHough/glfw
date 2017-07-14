(ns glfw.implementation
  (:import org.lwjgl.system.MemoryUtil
           org.lwjgl.PointerBuffer
           [org.lwjgl.vulkan
             VkAllocationCallbacks
             VkInstance
             VkAllocationFunctionI
             VkReallocationFunctionI
             VkFreeFunctionI
             VkInternalAllocationNotificationI
             VkInternalFreeNotificationI]
           [org.lwjgl.glfw
             GLFW
             GLFWNativeCocoa
             GLFWNativeCocoa$Functions
             GLFWNativeEGL
             GLFWNativeEGL$Functions
             GLFWNativeGLX
             GLFWNativeGLX$Functions
             GLFWNativeNSGL
             GLFWNativeNSGL$Functions
             GLFWNativeWGL
             GLFWNativeWGL$Functions
             GLFWNativeWin32
             GLFWNativeWin32$Functions
             GLFWNativeX11
             GLFWNativeX11$Functions
             GLFWVulkan
             GLFWVulkan$Functions
             GLFWGammaRamp
             GLFWGammaRamp$Buffer
             GLFWImage
             GLFWImage$Buffer
             GLFWVidMode
             GLFWVidMode$Buffer
             GLFWCharCallbackI
             GLFWCharModsCallbackI
             GLFWCursorEnterCallbackI
             GLFWCursorPosCallbackI
             GLFWDropCallbackI
             GLFWErrorCallbackI
             GLFWFramebufferSizeCallbackI
             GLFWJoystickCallbackI
             GLFWKeyCallbackI
             GLFWMonitorCallbackI
             GLFWMouseButtonCallbackI
             GLFWScrollCallbackI
             GLFWWindowCloseCallbackI
             GLFWWindowFocusCallbackI
             GLFWWindowIconifyCallbackI
             GLFWWindowMaximizeCallbackI
             GLFWWindowPosCallbackI
             GLFWWindowRefreshCallbackI
             GLFWWindowSizeCallbackI]))

;; TODO cachify fn->x and x->fn with a shared cache (per fn) to eliminate degenerative behavior

(defn null->nil [val]
  (if (= 0 val) nil val))

(defn nil->null [val]
  (if (nil? val) 0 val))

(defn boolean->int [val]
  (cond (= val true) 1 (= val false) 0 :default val))

(defn int->boolean [val]
  (cond (= val 0) false (= val 1) true :default val))

(defn fn->GLFWCharCallback ^GLFWCharCallbackI [f]
  (reify GLFWCharCallbackI
    (invoke [_ window codepoint]
      (f (long window) (int codepoint)))))
(defn GLFWCharCallback->fn [^GLFWCharCallbackI this]
  (fn [window codepoint]
    (.invoke this (long window) (int codepoint))))

(defn fn->GLFWCharModsCallback ^GLFWCharModsCallbackI [f]
  (reify GLFWCharModsCallbackI
    (invoke [_ window codepoint mods]
      (f (long window) (int codepoint) (int mods)))))
(defn GLFWCharModsCallback->fn [^GLFWCharModsCallbackI this]
  (fn [window codepoint mods]
    (.invoke this (long window) (int codepoint) (int mods))))

(defn fn->GLFWCursorEnterCallback ^GLFWCursorEnterCallbackI [f]
  (reify GLFWCursorEnterCallbackI
    (invoke [_ window entered]
      (f (long window) (boolean entered)))))
(defn GLFWCursorEnterCallback->fn [^GLFWCursorEnterCallbackI this]
  (fn [window entered]
    (.invoke this (long window) (boolean entered))))

(defn fn->GLFWCursorPosCallback ^GLFWCursorPosCallbackI [f]
  (reify GLFWCursorPosCallbackI
    (invoke [_ window x y]
      (f (long window) (double x) (double y)))))
(defn GLFWCursorPosCallback->fn [^GLFWCursorPosCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (double x) (double y))))

(defn fn->GLFWDropCallback ^GLFWDropCallbackI [f]
  (reify GLFWDropCallbackI
    (invoke [_ window count names]
      (f (long window) (int count) (long names)))))
(defn GLFWDropCallback->fn [^GLFWDropCallbackI this]
  (fn [window count names]
    (.invoke this (long window) (int count) (long names))))

(defn fn->GLFWErrorCallback ^GLFWErrorCallbackI [f]
  (reify GLFWErrorCallbackI
    (invoke [_ error description]
      (f (long error) (long description)))))
(defn GLFWErrorCallback->fn [^GLFWErrorCallbackI this]
  (fn [error description]
    (.invoke this (long error) (long description))))

(defn fn->GLFWFramebufferSizeCallback ^GLFWFramebufferSizeCallbackI [f]
  (reify GLFWFramebufferSizeCallbackI
    (invoke [_ window width height]
      (f (long window) (int width) (int height)))))
(defn GLFWFramebufferSizeCallback->fn [^GLFWFramebufferSizeCallbackI this]
  (fn [window width height]
    (.invoke this (long window) (int width) (int height))))

(defn fn->GLFWJoystickCallback ^GLFWJoystickCallbackI [f]
  (reify GLFWJoystickCallbackI
    (invoke [_ jid event]
      (f (int jid) (int event)))))
(defn GLFWJoystickCallback->fn [^GLFWJoystickCallbackI this]
  (fn [jid event]
    (.invoke this (int jid) (int event))))

(defn fn->GLFWKeyCallback ^GLFWKeyCallbackI [f]
  (reify GLFWKeyCallbackI
    (invoke [_ window key scancode action mods]
      (f (long window) (int key) (int scancode) (int action) (int mods)))))
(defn GLFWKeyCallback->fn [^GLFWKeyCallbackI this]
  (fn [window key scancode action mods]
    (.invoke this (long window) (int key) (int scancode) (int action) (int mods))))

(defn fn->GLFWMonitorCallback ^GLFWMonitorCallbackI [f]
  (reify GLFWMonitorCallbackI
    (invoke [_ monitor event]
      (f (long monitor) (int event)))))
(defn GLFWMonitorCallback->fn [^GLFWMonitorCallbackI this]
  (fn [monitor event]
    (.invoke this (long monitor) (int event))))

(defn fn->GLFWMouseButtonCallback ^GLFWMouseButtonCallbackI [f]
  (reify GLFWMouseButtonCallbackI
    (invoke [_ window button action mods]
      (f (long window) (int button) (int action) (int mods)))))
(defn GLFWMouseButtonCallback->fn [^GLFWMouseButtonCallbackI this]
  (fn [window button action mods]
    (.invoke this (long window) (int button) (int action) (int mods))))

(defn fn->GLFWScrollCallback ^GLFWScrollCallbackI [f]
  (reify GLFWScrollCallbackI
    (invoke [_ window x y]
      (f (long window) (double x) (double y)))))
(defn GLFWScrollCallback->fn [^GLFWScrollCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (double x) (double y))))

(defn fn->GLFWScrollCallback ^GLFWScrollCallbackI [f]
  (reify GLFWScrollCallbackI
    (invoke [_ window x y]
      (f (long window) (double x) (double y)))))
(defn GLFWScrollCallback->fn [^GLFWScrollCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (double x) (double y))))

(defn fn->GLFWWindowCloseCallback ^GLFWWindowCloseCallbackI [f]
  (reify GLFWWindowCloseCallbackI
    (invoke [_ window]
      (f (long window)))))
(defn GLFWWindowCloseCallback->fn [^GLFWWindowCloseCallbackI this]
  (fn [window]
    (.invoke this (long window))))

(defn fn->GLFWWindowFocusCallback ^GLFWWindowFocusCallbackI [f]
  (reify GLFWWindowFocusCallbackI
    (invoke [_ window focused]
      (f (long window) (boolean focused)))))
(defn GLFWWindowFocusCallback->fn [^GLFWWindowFocusCallbackI this]
  (fn [window focused]
    (.invoke this (long window) (boolean focused))))

(defn fn->GLFWWindowIconifyCallback ^GLFWWindowIconifyCallbackI [f]
  (reify GLFWWindowIconifyCallbackI
    (invoke [_ window iconified]
      (f (long window) (boolean iconified)))))
(defn GLFWWindowIconifyCallback->fn [^GLFWWindowIconifyCallbackI this]
  (fn [window iconified]
    (.invoke this (long window) (boolean iconified))))

(defn fn->GLFWWindowMaximizeCallback ^GLFWWindowMaximizeCallbackI [f]
  (reify GLFWWindowMaximizeCallbackI
    (invoke [_ window maximized]
      (f (long window) (boolean maximized)))))
(defn GLFWWindowMaximizeCallback->fn [^GLFWWindowMaximizeCallbackI this]
  (fn [window maximized]
    (.invoke this (long window) (boolean maximized))))

(defn fn->GLFWWindowPosCallback ^GLFWWindowPosCallbackI [f]
  (reify GLFWWindowPosCallbackI
    (invoke [_ window x y]
      (f (long window) (int x) (int y)))))
(defn GLFWWindowPosCallback->fn [^GLFWWindowPosCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (int x) (int y))))

(defn fn->GLFWWindowRefreshCallback ^GLFWWindowRefreshCallbackI [f]
  (reify GLFWWindowRefreshCallbackI
    (invoke [_ window]
      (f (long window)))))
(defn GLFWWindowRefreshCallback->fn [^GLFWWindowRefreshCallbackI this]
  (fn [window]
    (.invoke this (long window))))

(defn fn->GLFWWindowSizeCallback ^GLFWWindowSizeCallbackI [f]
  (reify GLFWWindowSizeCallbackI
    (invoke [_ window width height]
      (f (long window) (int width) (int height)))))
(defn GLFWWindowSizeCallback->fn [^GLFWWindowSizeCallbackI this]
  (fn [window width height]
    (.invoke this (long window) (int width) (int height))))

(defn fn->VkAllocationFunction ^VkAllocationFunctionI [f]
  (reify VkAllocationFunctionI
    (invoke [_ user-data size alignment allocation-scope]
      (f (long user-data) (long size) (long alignment) (int allocation-scope)))))

(defn fn->VkReallocationFunction ^VkReallocationFunctionI [f]
  (reify VkReallocationFunctionI
    (invoke [_ user-data original size alignment allocation-scope]
      (f (long user-data) (long original) (long size) (long alignment) (int allocation-scope)))))

(defn fn->VkFreeFunction ^VkFreeFunctionI [f]
  (reify VkFreeFunctionI
    (invoke [_ user-data memory]
      (f (long user-data) (long memory)))))

(defn fn->VkInternalAllocationNotification ^VkInternalAllocationNotificationI [f]
  (reify VkInternalAllocationNotificationI
    (invoke [_ user-data size allocation-type allocation-scope]
      (f (long user-data) (long size) (int allocation-type) (int allocation-scope)))))

(defn fn->VkInternalFreeNotification ^VkInternalFreeNotificationI [f]
  (reify VkInternalFreeNotificationI
    (invoke [_ user-data size allocation-type allocation-scope]
      (f (long user-data) (long size) (int allocation-type) (int allocation-scope)))))

(defmulti free class)
(defmethod free :default [this] nil)

(defn str->ByteBuffer
  ^java.nio.ByteBuffer
  [string]
  (MemoryUtil/memUTF8 string))
(defn ByteBuffer->str [this]
  (MemoryUtil/memUTF8 this (.limit this) 0))

(defn seq->ByteBuffer ^java.nio.ByteBuffer [s]
  (doto (MemoryUtil/memAlloc (count s))
    (.put (into-array Byte/TYPE s))
    (.flip)))
(defmethod free java.nio.ByteBuffer [this]
  (MemoryUtil/memFree this))
(defn ByteBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->FloatBuffer ^java.nio.FloatBuffer [s]
  (doto (MemoryUtil/memAllocFloat (count s))
    (.put (into-array Float/TYPE s))
    (.flip)))
(defmethod free java.nio.FloatBuffer [this]
  (MemoryUtil/memFree this))
(defn FloatBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->DoubleBuffer ^java.nio.DoubleBuffer [s]
  (doto (MemoryUtil/memAllocDouble (count s))
    (.put (into-array Double/TYPE s))
    (.flip)))
(defmethod free java.nio.DoubleBuffer [this]
  (MemoryUtil/memFree this))
(defn DoubleBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->ShortBuffer ^java.nio.ShortBuffer [s]
  (doto (MemoryUtil/memAllocShort (count s))
    (.put (into-array Short/TYPE s))
    (.flip)))
(defmethod free java.nio.ShortBuffer [this]
  (MemoryUtil/memFree this))
(defn ShortBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->IntBuffer ^java.nio.IntBuffer [s]
  (doto (MemoryUtil/memAllocInt (count s))
    (.put (into-array Integer/TYPE s))
    (.flip)))
(defmethod free java.nio.IntBuffer [this]
  (MemoryUtil/memFree this))
(defn IntBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->LongBuffer ^java.nio.LongBuffer [s]
  (doto (MemoryUtil/memAllocLong (count s))
    (.put (into-array Long/TYPE s))
    (.flip)))
(defmethod free java.nio.LongBuffer [this]
  (MemoryUtil/memFree this))
(defn LongBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->PointerBuffer ^PointerBuffer [s]
  (doto (MemoryUtil/memAllocPointer (count s))
    (.put (into-array Long/TYPE s))
    (.flip)))
(defmethod free PointerBuffer [this]
  (MemoryUtil/memFree this))
(defn PointerBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn map->GLFWGammaRamp [{:keys [red green blue size]}]
  (.set (GLFWGammaRamp/malloc)
    (seq->ShortBuffer red)
    (seq->ShortBuffer green)
    (seq->ShortBuffer blue)
    (int size)))
(defmethod free GLFWGammaRamp [this]
  (free (.red this))
  (free (.green this))
  (free (.blue this))
  (.free this))
(defn GLFWGammaRamp->map [this]
  {:red (ShortBuffer->seq (.red this))
   :green (ShortBuffer->seq (.green this))
   :blue (ShortBuffer->seq (.blue this))
   :size (int (.size this))})
(defn seq->GLFWGammaRamp$Buffer [s]
  (let [buffer (GLFWGammaRamp/malloc (count s))]
    (doseq [{:keys [red green blue size]} s]
      (.set (.get buffer)
        (seq->ShortBuffer red)
        (seq->ShortBuffer green)
        (seq->ShortBuffer blue)
        (int size)))
    (.flip buffer)))
(defmethod free GLFWGammaRamp$Buffer [this]
  (dotimes [n (.limit this)]
    (let [this (.get this n)]
      (free (.red this))
      (free (.green this))
      (free (.blue this))))
  (.free this))
(defn GLFWGammaRamp$Buffer->seq [this]
  (into [] (map GLFWGammaRamp->map) (for [i (range (.limit this))] (.get this i))))

(defn map->GLFWImage [{:keys [width height pixels]}]
  (.set (GLFWImage/malloc)
    (int width)
    (int height)
    (seq->ByteBuffer pixels)))
(defmethod free GLFWImage [this]
  (free (.pixels this))
  (.free this))
(defn GLFWImage->map [this]
  {:width (int (.width this))
   :height (int (.height this))
   :pixels (ByteBuffer->seq (.pixels this))})
(defn seq->GLFWImage$Buffer [s]
  (let [buffer (GLFWImage/malloc (count s))]
    (doseq [{:keys [width height pixels]} s]
      (.set (.get buffer)
        (int width)
        (int height)
        (seq->ByteBuffer pixels)))
    (.flip buffer)))
(defmethod free GLFWImage$Buffer [this]
  (dotimes [n (.limit this)]
    (let [this (.get this n)]
      (free (.pixels this))))
  (.free this))
(defn GLFWImage$Buffer->seq [this]
  (into [] (map GLFWImage->map) (for [i (range (.limit this))] (.get this i))))

(defn GLFWVidMode->map [this]
  {:width (int (.width this))
   :height (int (.height this))
   :red-bits (int (.redBits this))
   :green-bits (int (.greenBits this))
   :blue-bits (int (.blueBits this))
   :refresh-rate (int (.refreshRate this))})
(defmethod free GLFWVidMode [this]
  (.free this))
(defn GLFWVidMode$Buffer->seq [this]
  (into [] (map GLFWVidMode->map) (for [i (range (.limit this))] (.get this i))))
(defmethod free GLFWVidMode$Buffer [this]
  (.free this))

(defn map->VkAllocationCallbacks
  ^VkAllocationCallbacks
  [{:keys [user-data allocation reallocation free internal-allocation internal-free]}]
  (.set (VkAllocationCallbacks/malloc)
    (long user-data)
    (fn->VkAllocationFunction allocation)
    (fn->VkReallocationFunction reallocation)
    (fn->VkFreeFunction free)
    (fn->VkInternalAllocationNotification internal-allocation)
    (fn->VkInternalFreeNotification internal-free)))
(defmethod free VkAllocationCallbacks [this]
  (.free this))

(defn cocoa []
  {:monitor GLFWNativeCocoa$Functions/GetCocoaMonitor
   :window GLFWNativeCocoa$Functions/GetCocoaWindow})

(defn get-cocoa-window ^long [window]
  (GLFWNativeCocoa/glfwGetCocoaWindow (long window)))

(defn get-cocoa-monitor [monitor]
  (GLFWNativeCocoa/glfwGetCocoaMonitor (long monitor)))

(defn egl []
  {:context GLFWNativeEGL$Functions/GetEGLContext
   :display GLFWNativeEGL$Functions/GetEGLDisplay
   :surface GLFWNativeEGL$Functions/GetEGLSurface})

(defn get-egl-context ^long [window]
  (GLFWNativeEGL/glfwGetEGLContext (long window)))

(defn get-egl-display ^long []
  (GLFWNativeEGL/glfwGetEGLDisplay))

(defn get-egl-surface ^long [window]
  (GLFWNativeEGL/glfwGetEGLSurface (long window)))

(defn glx []
  {:context GLFWNativeGLX$Functions/GetGLXContext
   :window GLFWNativeGLX$Functions/GetGLXWindow})

(defn get-glx-context ^long [window]
  (GLFWNativeGLX/glfwGetGLXContext (long window)))

(defn get-glx-window ^long [window]
  (GLFWNativeGLX/glfwGetGLXWindow (long window)))

(defn nsgl []
  {:context GLFWNativeNSGL$Functions/GetNSGLContext})

(defn get-nsgl-context ^long [window]
  (GLFWNativeNSGL/glfwGetNSGLContext (long window)))

(defn wgl []
  {:context GLFWNativeWGL$Functions/GetWGLContext})

(defn get-wgl-context ^long [window]
  (GLFWNativeWGL/glfwGetWGLContext (long window)))

(defn win32 []
  {:adapter GLFWNativeWin32$Functions/GetWin32Adapter
   :monitor GLFWNativeWin32$Functions/GetWin32Monitor
   :window GLFWNativeWin32$Functions/GetWin32Window})

(defn get-win32-adapter ^java.lang.String [monitor]
  (GLFWNativeWin32/glfwGetWin32Adapter (long monitor)))

(defn get-win32-monitor ^java.lang.String [monitor]
  (GLFWNativeWin32/glfwGetWin32Monitor (long monitor)))

(defn get-win32-window ^long [window]
  (GLFWNativeWin32/glfwGetWin32Window (long window)))

(defn x11 []
  {:adapter GLFWNativeX11$Functions/GetX11Adapter
   :display GLFWNativeX11$Functions/GetX11Display
   :monitor GLFWNativeX11$Functions/GetX11Monitor
   :window GLFWNativeX11$Functions/GetX11Window})

(defn get-x11-adapter ^long [monitor]
  (GLFWNativeX11/glfwGetX11Adapter (long monitor)))

(defn get-x11-display ^long []
  (GLFWNativeX11/glfwGetX11Display))

(defn get-x11-monitor ^long [monitor]
  (GLFWNativeX11/glfwGetX11Monitor (long monitor)))

(defn get-x11-window ^long [window]
  (GLFWNativeX11/glfwGetX11Window (long window)))

(defn create-window-surface [^VkInstance instance window allocator surface]
  (let [INSTANCE instance
        WINDOW (long window)
        ALLOCATOR (map->VkAllocationCallbacks allocator)
        SURFACE (seq->LongBuffer surface)
        RESULT (GLFWVulkan/glfwCreateWindowSurface INSTANCE WINDOW ALLOCATOR SURFACE)]
    (free ALLOCATOR)
    (free SURFACE)
    RESULT))

(defn get-instance-proc-address ^long [^VkInstance instance procname]
  (let [INSTANCE instance
        PROCNAME (str->ByteBuffer procname)
        RESULT (GLFWVulkan/glfwGetInstanceProcAddress INSTANCE PROCNAME)]
    (free PROCNAME)
    RESULT))

(defn get-physical-device-presentation-support [^VkInstance instance device queue-family]
  (let [INSTANCE instance
        DEVICE device
        QUEUE-FAMILY (int queue-family)]
    (GLFWVulkan/glfwGetPhysicalDevicePresentationSupport INSTANCE DEVICE QUEUE-FAMILY)))

(defn get-required-instance-extensions []
  (into [] (map (fn [i] (MemoryUtil/memASCII i))) (PointerBuffer->seq (GLFWVulkan/glfwGetRequiredInstanceExtensions))))

(defn vulkan-supported []
  (GLFWVulkan/glfwVulkanSupported))

(defn vulkan []
  {:create-window-surface GLFWVulkan$Functions/CreateWindowSurface
   :get-instance-proc-address GLFWVulkan$Functions/GetInstanceProcAddress
   :get-physical-device-presentation-support GLFWVulkan$Functions/GetPhysicalDevicePresentationSupport
   :get-required-instance-extensions GLFWVulkan$Functions/GetRequiredInstanceExtensions
   :vulkan-supported GLFWVulkan$Functions/VulkanSupported})

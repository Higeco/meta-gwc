FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# We need some libraries from wpa_supplicant for GWC eos, so
# we build them as static libraries and then deliver them (and the headers)
# in the dev and staticdev part that will be delivered in the SDK

# The package makefile will create tiny libraries (which need also the .o
# being also there), patch it to have a normal .a delivery
SRC_URI+= " file://0001-Static_libs_for_dev_2_10.patch"

# Additional libraries build
do_compile:append () {
	oe_runmake -C src
}

# Additional libraries/headers install
do_install:append () {
	install -d ${D}/usr/lib/wpa_supplicant
	install -d ${D}/usr/include/wpa_supplicant
	install -m 0644 ${S}/src/utils/libutils.a ${D}/usr/lib/wpa_supplicant
	install -m 0644 ${S}/src/common/libcommon.a ${D}/usr/lib/wpa_supplicant
	install -m 0644 ${S}/src/utils/*.h ${D}/usr/include/wpa_supplicant
	install -m 0644 ${S}/src/common/*.h ${D}/usr/include/wpa_supplicant

}

FILES:${PN}-staticdev += "/usr/lib/wpa_supplicant/libutils.a /usr/lib/wpa_supplicant/libcommon.a"

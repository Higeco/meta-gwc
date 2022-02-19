DESCRIPTION = "lib60870 an implementation of the IEC 60870-5-101/104 protocol "
LICENSE = "GPLv3"
HOMEPAGE = "https://www.mz-automation.de/communication-protocols/iec-60870-5-101-104-c-source-code-library/"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/mz-automation/lib60870/archive/refs/tags/v${PV}.tar.gz;sha256sum=643c30383eb06dbec6536d30252c05fca3830ac138ff9309f18a5d9dc03e0ee8 \
           https://github.com/ARMmbed/mbedtls/archive/refs/tags/mbedtls-2.16.12.tar.gz;sha256sum=0afb4a4ce5b771f2fb86daee786362fbe48285f05b73cd205f46a224ec031783 \
           file://0001-Build_tweaks.patch"

# We need to include in the build the specific mbedtls 2.16.12 version as testing with newer versions
# does not seem to work

inherit autotools

S = "${WORKDIR}/lib60870-${PV}"

do_compile() {
   # Prepare mbedtls needed links
   cd ${S}/lib60870-C/dependencies
   rm -rf mbedtls-2.16.12
   mkdir mbedtls-2.16.12
   cd mbedtls-2.16.12
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.16.12/include
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.16.12/library

   # Build
   cd ${S}/lib60870-C
   oe_runmake WITH_MBEDTLS=1
}


do_install() {
   cd ${S}/lib60870-C
   export INSTALL_PREFIX=${D}/usr
   oe_runmake install
}

# Being a static library the main package will be empty
ALLOW_EMPTY_${PN} = "1"

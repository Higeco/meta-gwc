DESCRIPTION = "lib60870 an implementation of the IEC 60870-5-101/104 protocol "
LICENSE = "GPLv3"
HOMEPAGE = "https://www.mz-automation.de/communication-protocols/iec-60870-5-101-104-c-source-code-library/"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/mz-automation/lib60870/archive/refs/tags/v${PV}.tar.gz;sha256sum=982bc415b4c3b0c9edf08a760417ba567de6ab33d975edad306dc5280ff5e0cf \
           https://github.com/ARMmbed/mbedtls/archive/refs/tags/mbedtls-2.6.0.tar.gz;sha256sum=8ed1e4ef5831968847565e7da1ed9cc0e9aea419da324326ae24912a03c12a1b \
           file://0001-Build_tweaks.patch"

# We need to include in the build the specific mbedtls 2.6.0 version as testing with newer versions
# does not seem to work

inherit autotools

S = "${WORKDIR}/lib60870-${PV}"

do_compile() {
   # Prepare mbedtls needed links
   cd ${S}/lib60870-C/dependencies
   rm -rf mbedtls-2.6.0
   mkdir mbedtls-2.6.0
   cd mbedtls-2.6.0
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.6.0/include
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.6.0/library

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

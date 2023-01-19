DESCRIPTION = "lib60870 an implementation of the IEC 60870-5-101/104 protocol "
LICENSE = "GPL-3.0-or-later"
HOMEPAGE = "https://www.mz-automation.de/communication-protocols/iec-60870-5-101-104-c-source-code-library/"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/mz-automation/lib60870/archive/refs/tags/v${PV}.tar.gz;sha256sum=c63f170dc2dd25f7ec85d873be522f23d46838a84b072c8afda4118dfd5fc94d \
           https://github.com/ARMmbed/mbedtls/archive/refs/tags/mbedtls-2.28.2.tar.gz;sha256sum=1db6d4196178fa9f8264bef5940611cd9febcd5d54ec05f52f1e8400f792b5a4 \
           file://0001-Build_tweaks.patch"

# We need to include in the build the specific mbedtls 2.16.12 version as testing with newer versions
# does not seem to work

inherit autotools

S = "${WORKDIR}/lib60870-${PV}"

do_compile() {
   # Prepare mbedtls needed links
   cd ${S}/lib60870-C/dependencies
   rm -rf mbedtls-2.28
   mkdir mbedtls-2.28
   cd mbedtls-2.28
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.28.2/include
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.28.2/library

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
ALLOW_EMPTY:${PN} = "1"

DESCRIPTION = "libIEC61850, the open-source library for the IEC 61850 protocols"
LICENSE = "GPLv3"
HOMEPAGE = "https://libiec61850.com/"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/mz-automation/libiec61850/archive/refs/tags/v${PV}.tar.gz;sha256sum=7b832c195ae9f42faa1ccfe1b82b9ff187103155ce45aaca08881be98459d164 \
           https://github.com/ARMmbed/mbedtls/archive/refs/tags/mbedtls-2.16.0.tar.gz;sha256sum=cd14ff7b422eac01516973e41252c67c274814783b32fafb91a63ebe946beaff \
           file://0001-Build_tweaks.patch"


inherit autotools

S = "${WORKDIR}/libiec61850-${PV}"

do_compile() {
   # Prepare mbedtls needed links
   cd ${S}/third_party/mbedtls
   rm -rf mbedtls-2.16  
   mkdir mbedtls-2.16
   cd mbedtls-2.16
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.16.0/include
   ln -s ${WORKDIR}/mbedtls-mbedtls-2.16.0/library  

   cd ${S}
   oe_runmake WITH_MBEDTLS=1
}


do_install() {
   cd ${S}
   export INSTALL_PREFIX=${D}/usr
   oe_runmake install
}

# Being a static library the main package will be empty
ALLOW_EMPTY:${PN} = "1"

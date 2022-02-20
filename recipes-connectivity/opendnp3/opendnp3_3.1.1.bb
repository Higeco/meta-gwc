DESCRIPTION = "OpenDNP3 library"
DEPENDS="boost asio openssl"
LICENSE="Apache-2.0"
HOMEPAGE = "https://dnp3.github.io/"
LIC_FILES_CHKSUM = "file://config/APACHE_LICENSE_HEADER;md5=7c4120df14f2e4569013c3d3de71a778"

SRC_URI = "https://github.com/dnp3/opendnp3/archive/${PV}.tar.gz;sha256sum=4012f1a1837b164a8be3156ba0b42919a94161a45b08d9243667fcb2833405da \
           https://github.com/automatak/exe4cpp/archive/fb878a4de598ba9d6e4338afebf83f96e03af1b8.zip;sha256sum=2a8a2c7d9982f54858b92c03cff91ef433907472039f8e61ff7c9f9b3d48db6c \
           https://github.com/automatak/ser4cpp/archive/3c449734dc530a8f465eb0982de29165cc4e23d5.zip;sha256sum=5a78f83de2d70bdf4be2ab607bc4416c6866ce64a257a8f19948cf5746762f6e \
           file://0001-Patch_no_download_deps.patch"

inherit cmake

S="${WORKDIR}/opendnp3-${PV}"

# Enable TLS support
EXTRA_OECMAKE += "-DDNP3_TLS=ON"

# Header only libraries, just refer to them directly
TARGET_CFLAGS += "-I${WORKDIR}/exe4cpp-fb878a4de598ba9d6e4338afebf83f96e03af1b8/src -I${WORKDIR}/ser4cpp-3c449734dc530a8f465eb0982de29165cc4e23d5/src"

FILES:${PN}-dev = "/usr/include/opendnp3 /usr/lib/cmake"
FILES:${PN} = "${libdir}/libopendnp3.so"

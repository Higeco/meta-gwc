DESCRIPTION = "OpenDNP3 library"
DEPENDS="boost asio openssl"
LICENSE="Apache-2.0"
HOMEPAGE = "https://dnp3.github.io/"
LIC_FILES_CHKSUM = "file://config/APACHE_LICENSE_HEADER;md5=7c4120df14f2e4569013c3d3de71a778"


SRC_URI = "https://github.com/dnp3/opendnp3/archive/${PV}.tar.gz;sha256sum=183cc29222c3cb58099a9753c43defed5cb8677fda985c3f88e38b5c19a36ff2 \
           https://github.com/automatak/exe4cpp/archive/fb878a4de598ba9d6e4338afebf83f96e03af1b8.tar.gz;sha256sum=f86023c6504e3bfee6f78faad1b4ed2d50f6c3173b1040585c3621b8236579da;striplevel=1;subdir=opendnp3-${PV}/deps/exe4cpp; \
           https://github.com/automatak/ser4cpp/archive/3c449734dc530a8f465eb0982de29165cc4e23d5.tar.gz;sha256sum=1ff34b250cf7de420e58407bf5107f311a85014912fd2a6581ffd7278337933b;striplevel=1;subdir=opendnp3-${PV}/deps/ser4cpp; \
           file://0001-Patch_no_download_deps.patch"

inherit cmake

S="${WORKDIR}/opendnp3-${PV}"

# Enable TLS support
EXTRA_OECMAKE += "-DDNP3_TLS=ON"

# Header only libraries, just refer to them directly
TARGET_CFLAGS += "-I${S}/deps/exe4cpp/src -I${S}/deps/ser4cpp/src"

FILES:${PN}-dev = "/usr/include/opendnp3 /usr/lib/cmake"
FILES:${PN} = "${libdir}/libopendnp3.so"

INSANE_SKIP:${PN} += " src-uri-bad "

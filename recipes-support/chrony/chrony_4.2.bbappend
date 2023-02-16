
inherit pkgconfig

DEPENDS += "nettle gnutls " 
RCONFLICTS:${PN} = "ntimed"

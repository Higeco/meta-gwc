FILESEXTRAPATHS_prepend := "${THISDIR}/cmake:"

# Curl 8.x breaking change, see also: https://gitlab.kitware.com/cmake/cmake/-/merge_requests/10449
SRC_URI += " file://0001-Curl_upgrade_breaking_compat_change.patch "

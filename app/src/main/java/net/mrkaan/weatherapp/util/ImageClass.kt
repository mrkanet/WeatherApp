package net.mrkaan.weatherapp.util

import net.mrkaan.weatherapp.R

class ImageClass() {
    companion object {
        fun getImage(imageName: String): Int {
            return when (imageName) {
                "a01d" -> R.drawable.a01d
                "a01n" -> R.drawable.a01n
                "a02d" -> R.drawable.a02d
                "a02n" -> R.drawable.a02n
                "a03d" -> R.drawable.a03d
                "a03n" -> R.drawable.a03n
                "a04d" -> R.drawable.a04d
                "a04n" -> R.drawable.a04n
                "a05d" -> R.drawable.a05d
                "a05n" -> R.drawable.a05n
                "a06d" -> R.drawable.a06d
                "a06n" -> R.drawable.a06n
                "c01d" -> R.drawable.c01d
                "c01n" -> R.drawable.c01n
                "c02d" -> R.drawable.c02d
                "c02n" -> R.drawable.c02n
                "c03d" -> R.drawable.c03d
                "c03n" -> R.drawable.c03n
                "c04d" -> R.drawable.c04d
                "c04n" -> R.drawable.c04n
                "d01d" -> R.drawable.d01d
                "d01n" -> R.drawable.d01n
                "d02d" -> R.drawable.d02d
                "d02n" -> R.drawable.d02n
                "d03d" -> R.drawable.d03d
                "d03n" -> R.drawable.d03n
                "f01d" -> R.drawable.f01d
                "f01n" -> R.drawable.f01n
                "r01d" -> R.drawable.r01d
                "r01n" -> R.drawable.r01n
                "r02d" -> R.drawable.r02d
                "r02n" -> R.drawable.r02n
                "r03d" -> R.drawable.r03d
                "r03n" -> R.drawable.r03n
                "r04d" -> R.drawable.r04d
                "r04n" -> R.drawable.r04n
                "r05d" -> R.drawable.r05d
                "r05n" -> R.drawable.r05n
                "r06d" -> R.drawable.r06d
                "r06n" -> R.drawable.r06n
                "s01d" -> R.drawable.s01d
                "s01n" -> R.drawable.s01n
                "s02d" -> R.drawable.s02d
                "s02n" -> R.drawable.s02n
                "s03d" -> R.drawable.s03d
                "s03n" -> R.drawable.s03n
                "s04d" -> R.drawable.s04d
                "s04n" -> R.drawable.s04n
                "s05d" -> R.drawable.s05d
                "s05n" -> R.drawable.s05n
                "s06d" -> R.drawable.s06d
                "s06n" -> R.drawable.s06n
                "t01d" -> R.drawable.t01d
                "t01n" -> R.drawable.t01n
                "t02d" -> R.drawable.t02d
                "t02n" -> R.drawable.t02n
                "t03d" -> R.drawable.t03d
                "t03n" -> R.drawable.t03n
                "t04d" -> R.drawable.t04d
                "t04n" -> R.drawable.t04n
                "t05d" -> R.drawable.t05d
                "t05n" -> R.drawable.t05n
                "u00d" -> R.drawable.u00d
                "u00n" -> R.drawable.u00n
                else -> R.drawable.c01d
            }
        }
    }
}
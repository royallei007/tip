#!/usr/bin/env ruby

require 'pry'

str = '010010010010000001100001011011010010000001110011011011110010000001101000011010010110011101101000001000000110111101101110001000000110000101100011011010010110010000100000011000010110111001100100001000000100100100100000011000110110000101101110001000000110111101101110011011000111100100100000011100110111000001100101011000010110101100100000011010010110111000100000011000100110100101101110011000010111001001111001001000000110001101101111011001000110010100100000011011100110111101110111'

str = '010001000111001001101111011100000010000001100001011000110110100101100100'

str='01101001 01101101 01100001 01100111 01101001 01101110 01100101 00100000 01101000 01101111 01110111 00100000 01100010 01101001 01100111 00100000 01100010 01101111 01101111 01101011 01110011 00100000 01110111 01101111 01110101 01101100 01100100 00100000 01100010 01100101 00100000 00101100 00100000 01101001 01100110 00100000 01110100 01101000 01100101 01111001 00100000 01110111 01100101 01110010 01100101 00100000 01110111 01110010 01101001 01110100 01110100 01100101 01101110 00100000 01101001 01101110 00100000 01000010 01101001 00100000 01101110 01100001 00100000 01110010 01111001 00100000 00101110 00100000 01101100 01101111 01101100'.split.join

str = ' 596f752061726520736f20676179'

str='01001001 00100000 01100010 01101111 01110101 01100111 01101000 01110100 00100000 01110011 01101111 01101101 01100101 00100000 01100111 01110010 01100001 01110000 01100101 01110011 00100000 01101001 01101110 01110100 01101111 00100000 01110011 01100011 01101000 01101111 01101111 01101100 00100000 01100110 01101111 01110010 00100000 01101101 01111001 00100000 01101101 01100001 01110100 01100101 01110011 00100000 01100001 01101110 01100100 00100000 01101110 01101111 01110111 00100000 01110100 01101000 01100001 01111001 00100000 01100001 01101100 01101100 00100000 01100011 01100001 01101100 01101100 00100000 01101101 01100101 00100000 01100110 01110010 01110101 01101001 01110100 00100000 01100100 01100001 01100100 01100100 01111001 00100000 01110011 01101111 01101101 01100010 01101111 01100100 01111001 00100000 01101000 01100101 01101100 01110000 00100000 01101101 01100101 00101110 00100000 00001101 00001010'.split.join

srt = ' 01001001 00100000 01100010 01101111 01110101 01100111 01101000 01110100 00100000 01110011 01101111 01101101 01100101 00100000 01100111 01110010 01100001 01110000 01100101 01110011 00100000 01101001 01101110 01110100 01101111 00100000 01110011 01100011 01101000 01101111 01101111 01101100 00100000 01100110 01101111 01110010 00100000 01101101 01111001 00100000 01101101 01100001 01110100 01100101 01110011 00100000 01100001 01101110 01100100 00100000 01101110 01101111 01110111 00100000 01110100 01101000 01100001 01111001 00100000 01100001 01101100 01101100 00100000 01100011 01100001 01101100 01101100 00100000 01101101 01100101 00100000 01100110 01110010 01110101 01101001 01110100 00100000 01100100 01100001 01100100 01100100 01111001 00100000 01110011 01101111 01101101 01100010 01101111 01100100 01111001 00100000 01101000 01100101 01101100 01110000 00100000 01101101 01100101 00101110 00100000 00001101 00001010'.split.join

puts str.scan(/.{8}/).map{ |s| s.to_i(2).chr }.join

str = "triconduodecimal"
tri = str.split(//).map{ |s| s.ord.to_s(32) }.join

puts str.unpack('s*')
puts tri





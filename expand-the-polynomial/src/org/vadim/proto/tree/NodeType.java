package org.vadim.proto.tree;

public enum NodeType {
	EXPR, // BLOCK * BLOCK * ...
	BLOCK, // (..)
	POW_BLOCK, // (..)^m
	VALUE // value = K*x^M where M could be 0
	;
}

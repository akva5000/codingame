# Statement
## Goal

You are going to write a program to predict whether a specific usage pattern of electrical appliances will cause the main fuse to blow.

You have three pieces of data.

1. There are n appliances in a room, each of them has an electrical current consumption value.
2. A usage pattern: you will click the power buttons of a list of appliances in a sequence, totally m clicks.\n
   Each click on a button will toggle the power status - when the power is OFF, a click will turn it ON. The next click will turn it OFF.
3. The capacity of the main fuse c in amperes [A].

The fuse will be blown if the sum of the consumed current of turned-on devices at some point exceeds the capacity of the main fuse c.

At the beginning, all appliances are OFF. 


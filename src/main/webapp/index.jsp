<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guns Shop</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="GunData">
    <form action="${pageContext.request.contextPath}/parser" method="post">
        Weapon:<br>

        <div class="GunDataInput">
            Model:<input type="text" name="model" value="AK-47" required><span style="color: red; ">*</span><br>
            Origin: <input type="text" name="origin" value="USSR"><br>
            Handy: <select name="handy">
            <option value="Two_handed">Two_handed</option>
            <option value="One_handed">One_handed</option>
        </select><br>

            <div class="TTC">Firing range: <input type="number" name="Firing_range"><span
                    style="color: red; ">*</span><br>
                Effective firing range: <input type="number" name="Effective_firing_range" required><span
                        style="color: red; ">*</span><br>
                Cartridge clip availability: <input type="checkbox" name="artridge_clip_availability" required><span
                        style="color: red; ">*</span><br>
                Optics availabilty:<input type="checkbox" name="Optics_availability" required><span
                        style="color: red; ">*</span><br></div>
            Material: <input type="text" name="Material" value="armed plastic" required><br>
            <button type="submit">Add</button>
        </div>
    </form>
</div>
</body>
</html>

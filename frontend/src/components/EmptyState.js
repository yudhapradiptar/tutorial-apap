import React from "react";

export default function EmptytState(props) {
    return (
        <div>
            <h3 style={styles.heading}>My Favorite</h3>
            <h4>Belum ada Menu Favorit</h4>
            <p>Silahkan Pilih menu yang ada disamping</p>
        </div>
    );
}

const styles = {
    heading: {
        fontFamily: "courier new"
    }
};
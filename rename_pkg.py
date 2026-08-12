#!/usr/bin/env python3
import sys
import shutil
from pathlib import Path


def main():
    if len(sys.argv) != 3:
        print(f"Usage: {sys.argv[0]} <group_id> <app_package>", file=sys.stderr)
        sys.exit(1)

    group_id = sys.argv[1]
    app_package = sys.argv[2]

    # Copier runs tasks with the destination path as the current working
    # directory, so operate on "." rather than taking dst_path as an
    # argument (which may be relative, and would then be resolved against
    # a cwd that's already inside the destination — a nonexistent path).
    dst_path = Path(".")

    pkg_path = group_id.replace(".", "/") + "/" + app_package

    pkg_dirs = sorted(dst_path.rglob("__pkg__"), key=lambda p: len(p.parts), reverse=True)

    if not pkg_dirs:
        print("No __pkg__ directories found — nothing to rename.", file=sys.stderr)
        return

    for pkg_dir in pkg_dirs:
        if not pkg_dir.exists():
            continue
        target_dir = pkg_dir.parent / Path(pkg_path)
        target_dir.mkdir(parents=True, exist_ok=True)
        for item in list(pkg_dir.iterdir()):
            shutil.move(str(item), str(target_dir / item.name))
        pkg_dir.rmdir()

    print(f"Renamed __pkg__ -> {pkg_path}")


if __name__ == "__main__":
    main()
